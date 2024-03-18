package model;

public class Employee {
    private String maNV, hoTen, email;
    private double luong;
    private int tuoi;

    public Employee(String maNV, String hoTen, int tuoi ,String email ,double luong ) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.email = email;
        this.luong = luong;
        this.tuoi = tuoi;
    }

    public Employee() {
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }
}
