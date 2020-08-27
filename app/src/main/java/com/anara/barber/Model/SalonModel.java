package com.anara.barber.Model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class SalonModel implements Serializable {
    String ownerName;
    String ownerAddress;
    ArrayList<File> files;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }
}
