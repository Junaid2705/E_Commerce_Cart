package org.project.models;

public class Carts {
    private int uid;
    private int pid;
    private int cid; // New field for category ID
    private int quantity;

    // Getters and Setters
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

	@Override
	public String toString() {
		return "Carts [uid=" + uid + ", pid=" + pid + ", cid=" + cid + ", quantity=" + quantity + "]";
	}
    
}
