package com.renlore.wordnet;

import android.graphics.Point;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ng on 6/5/2015.
 */
public class Waypoint {
    private List<PointF> waypoints;
    private PointF currentLocation;
    private double tolerence;

    public Waypoint(PointF currentLocation, double tolerence) {
        this.currentLocation = currentLocation;
        this.tolerence = tolerence;
        waypoints = new ArrayList<PointF>();
    }

    public void addWP(PointF wp) {
        waypoints.add(wp);
    }

    public void update(PointF currentLocation) {
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
        return Math.toDegrees(Math.atan2(waypoints.get(0).y - currentLocation.y, waypoints.get(0).x - currentLocation.x));
    }

    public boolean waypointAvail() {
        return !waypoints.isEmpty();
    }
}
