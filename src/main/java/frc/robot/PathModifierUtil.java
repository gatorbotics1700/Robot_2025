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
     * Modifies a PathPlanner path file by moving the final waypoint along its rotation direction
     * @param pathName The name of the path file (without .path extension)
     * @param magnitude The distance to move the final waypoint
     * @return true if successful, false if an error occurred
     */
    public static boolean modifyPathFile(String pathName, double magnitude) {
        try {
            String deployDir = Paths.get(System.getProperty("user.dir"), "src", "main", "deploy").toString();
            String pathFile = Paths.get(deployDir, "pathplanner", "paths", pathName + ".path").toString();

            ObjectMapper mapper = new ObjectMapper();

            // Read the path file
            PathPlannerPath path = mapper.readValue(new File(pathFile), PathPlannerPath.class);

            // Modify the path
            modifyPath(path, magnitude);

            // Write back to the same file
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(pathFile), path);

            System.out.printf("Successfully modified path '%s'. Final waypoint moved %.3f units along rotation angle %.1f°%n", 
                            pathName, magnitude, path.goalEndState.rotation);
            return true;

        } catch (Exception e) {
            System.err.println("Error modifying path: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java PathModifierUtil <pathName> <magnitude>");
            System.out.println("Note: Don't include the .path extension in the path name");
            System.exit(1);
        }

        try {
            String pathName = args[0];
            double magnitude = Double.parseDouble(args[1]);

            boolean success = modifyPathFile(pathName, magnitude);
            if (!success) {
                System.exit(1);
            }

        } catch (NumberFormatException e) {
            System.err.println("Error: magnitude must be a valid number");
            System.exit(1);
        }
    }
}

// java -cp build/libs/Robot_2025.jar frc.robot.PathModifierUtil blue mid to ne 1 1