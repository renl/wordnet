package com.renlore.wordnet;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ng on 6/5/2015.
 */
public class Waypoint {
    private List<Point> waypoints = new ArrayList<Point>();
    private Point currentLocation;
    private double tolerence;

    public Waypoint(Point currentLocation, double tolerence) {
        this.currentLocation = currentLocation;
        this.tolerence = tolerence;
    }

    public void addWP(Point wp) {
        waypoints.add(wp);
    }

    public void update(Point currentLocation) {
        this.currentLocation = currentLocation;
        if (!waypoints.isEmpty()) {
            double dist = Math.hypot((double) (currentLocation.x - waypoints.get(0).x),
                    (double) (currentLocation.y - waypoints.get(0).y));
            if (dist < 20) {
                waypoints.remove(0);
            }
        }
    }

    public double getHeading() {
        return Math.atan2(waypoints.get(0).y - currentLocation.y, waypoints.get(0).y - currentLocation.y);
    }

    public boolean waypointAvail() {
        return !waypoints.isEmpty();
    }
}
