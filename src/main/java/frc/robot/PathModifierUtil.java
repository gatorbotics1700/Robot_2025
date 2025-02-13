package frc.robot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class PathModifierUtil {
    public static class Point {
        public double x;
        public double y;
    }

    public static class Waypoint {
        public Point anchor;
        public Point prevControl;
        public Point nextControl;
        public boolean isLocked;
        public Object linkedName;
    }

    public static class GoalEndState {
        public double velocity;
        public double rotation;
    }

    public static class IdealStartingState {
        public double velocity;
        public double rotation;
    }

    public static class GlobalConstraints {
        public double maxVelocity;
        public double maxAcceleration;
        public double maxAngularVelocity;
        public double maxAngularAcceleration;
        public double nominalVoltage;
        public boolean unlimited;
    }

    public static class PathPlannerPath {
        public String version;
        public List<Waypoint> waypoints;
        public List<Object> rotationTargets;
        public List<Object> constraintZones;
        public List<Object> pointTowardsZones;
        public List<Object> eventMarkers;
        public GlobalConstraints globalConstraints;
        public GoalEndState goalEndState;
        public boolean reversed;
        public String folder;
        public IdealStartingState idealStartingState;
        public boolean useDefaultConstraints;
    }

    private static void modifyPath(PathPlannerPath path, double magnitude) {
        // Only modify the last waypoint
        if (path.waypoints.isEmpty()) {
            return;
        }

        // Get the last waypoint
        Waypoint lastWaypoint = path.waypoints.get(path.waypoints.size() - 1);

        // Convert rotation from degrees to radians
        double rotationRad = Math.toRadians(path.goalEndState.rotation);

        // Calculate offset vector components
        // Note: Using negative sine for x because PathPlanner uses a different coordinate system
        // where positive rotation is counterclockwise from the positive X-axis
        double dx = -magnitude * Math.sin(rotationRad);
        double dy = magnitude * Math.cos(rotationRad);

        // Apply offset to the last waypoint
        lastWaypoint.anchor.x += dx;
        lastWaypoint.anchor.y += dy;
    }

    /**
     * Modifies a single PathPlanner path file
     * @param pathFile The File object representing the path file
     * @param magnitude The distance to move the final waypoint
     * @return true if successful, false if an error occurred
     */
    private static boolean modifyPathFile(File pathFile, double magnitude) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            // Read the path file
            PathPlannerPath path = mapper.readValue(pathFile, PathPlannerPath.class);

            // Modify the path
            modifyPath(path, magnitude);

            // Write back to the same file
            mapper.writerWithDefaultPrettyPrinter().writeValue(pathFile, path);

            System.out.printf("Successfully modified path '%s'. Final waypoint moved %.3f units along rotation angle %.1f°%n", 
                            pathFile.getName(), magnitude, path.goalEndState.rotation);
            return true;
        } catch (Exception e) {
            System.err.println("Error modifying path " + pathFile.getName() + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Modifies all PathPlanner path files in a directory
     * @param folderName The name of the folder within pathplanner/paths (or null/empty for all paths)
     * @param magnitude The distance to move the final waypoint
     * @return The number of successfully modified files
     */
    public static int modifyAllPathFiles(String folderName, double magnitude) {
        String deployDir = Paths.get(System.getProperty("user.dir"), "src", "main", "deploy").toString();
        File pathsDir = new File(Paths.get(deployDir, "pathplanner", "paths").toString());

        if (!pathsDir.exists() || !pathsDir.isDirectory()) {
            System.err.println("Error: PathPlanner paths directory not found");
            return 0;
        }

        File[] pathFiles = pathsDir.listFiles((dir, name) -> {
            if (!name.endsWith(".path")) {
                return false;
            }
            if (folderName == null || folderName.isEmpty()) {
                return true;
            }
            try {
                ObjectMapper mapper = new ObjectMapper();
                PathPlannerPath path = mapper.readValue(new File(dir, name), PathPlannerPath.class);
                return folderName.equals(path.folder);
            } catch (Exception e) {
                return false;
            }
        });

        if (pathFiles == null || pathFiles.length == 0) {
            System.out.println("No path files found" + 
                (folderName != null && !folderName.isEmpty() ? " in folder '" + folderName + "'" : ""));
            return 0;
        }

        int successCount = 0;
        for (File pathFile : pathFiles) {
            if (modifyPathFile(pathFile, magnitude)) {
                successCount++;
            }
        }

        System.out.printf("Modified %d out of %d path files%n", successCount, pathFiles.length);
        return successCount;
    }

    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.out.println("Usage:");
            System.out.println("  For single file: java PathModifierUtil <pathName> <magnitude>");
            System.out.println("  For all files: java PathModifierUtil --all <magnitude>");
            System.out.println("  For files in folder: java PathModifierUtil --folder <folderName> <magnitude>");
            System.exit(1);
        }

        try {
            if (args[0].equals("--all")) {
                double magnitude = Double.parseDouble(args[1]);
                modifyAllPathFiles(null, magnitude);
            } else if (args[0].equals("--folder")) {
                String folderName = args[1];
                double magnitude = Double.parseDouble(args[2]);
                modifyAllPathFiles(folderName, magnitude);
            } else {
                String pathName = args[0];
                double magnitude = Double.parseDouble(args[1]);
                File pathFile = new File(Paths.get(System.getProperty("user.dir"), "src", "main", "deploy",
                    "pathplanner", "paths", pathName + ".path").toString());
                modifyPathFile(pathFile, magnitude);
            }

        } catch (NumberFormatException e) {
            System.err.println("Error: magnitude must be a valid number");
            System.exit(1);
        }
    }
}

// java -cp build/libs/Robot_2025.jar frc.robot.PathModifierUtil blue mid to ne 1 1