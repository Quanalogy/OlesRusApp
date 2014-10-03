package oles.rus.app.olesrusapp;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by borimino on 9/25/14.
 */
public class Mission
{
    private int id;
    private LatLng geoPos;
    private int missionId;
    private int groupId;
    private int opposingGroupId;
    private int status;
    private int time;
    private int points;
    private boolean good;
    private String descriptionBefore;
    private String descriptionAfterIfWon;
    private String descriptionAfterIfLost;
    private String name;


    public Mission(int id, LatLng geoPos, int missionId, int groupId, int opposingGroupId, int status, boolean good, String descriptionBefore, String descriptionAfterIfWon, String descriptionAfterIfLost, String name)
    {
        this.id = id;
        this.geoPos = geoPos;
        this.missionId = missionId;
        this.groupId = groupId;
        this.opposingGroupId = opposingGroupId;
        this.status = status;
        this.good = good;
        this.descriptionBefore = descriptionBefore;
        this.descriptionAfterIfWon = descriptionAfterIfWon;
        this.descriptionAfterIfLost = descriptionAfterIfLost;
        this.name = name;
        this.time = 0;
        this.points = 0;
    }

    public void startTime()
    {

    }

    public void stopTime()
    {

    }

    public int getPoints()
    {
        return points;
    }

    public String getDescriptionBefore()
    {
        return descriptionBefore;
    }

    public String getDescriptionAfterIfWon()
    {
        return descriptionAfterIfWon;
    }

    public String getDescriptionAfterIfLost()
    {
        return descriptionAfterIfLost;
    }

    public int getId()
    {
        return id;
    }

    public int getOpposingGroupId()
    {
        return opposingGroupId;
    }

    public int getStatus()
    {
        return status;
    }

    public int getTime()
    {
        return time;
    }

    public boolean isGood()
    {
        return good;
    }

    public int getGroupId()
    {

        return groupId;
    }

    public LatLng getGeoPos()
    {

        return geoPos;
    }

    public String getName()
    {
        return name;
    }

    public int getMissionId()
    {

        return missionId;
    }

    public Marker addMarker()
    {
        Marker m = MapsActivity.getMapsActivity().getMap().addMarker(new MarkerOptions().position(geoPos).title("Mission"));
        return m;
    }
}
