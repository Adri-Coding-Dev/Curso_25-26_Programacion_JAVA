package postOffice;

import java.time.*;

public class Paquete {
    private long idPackage;
    private double weight;
    private double height;
    private double width;
    private String sender;
    private String receiver;
    private String address;
    private LocalDate dateShipped;
    private LocalDate pickUpDate;

    public Paquete(long idPackage, double weight, double height, double width, String sender, String receiver,
            String address, LocalDate dateShipped, LocalDate pickUpDate) {
        this.idPackage = idPackage;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.sender = sender;
        this.receiver = receiver;
        this.address = address;
        this.dateShipped = dateShipped;
        this.pickUpDate = pickUpDate;

    }

    // Getters
    public long getIdPackage() {
        return idPackage;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getDateShipped() {
        return dateShipped;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    // Setters
    public void setIdPackage(long idPackage) {
        this.idPackage = idPackage;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateShipped(LocalDate dateShipped) {
        this.dateShipped = dateShipped;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    @Override
    public String toString() {
        return "Paquete [ID: " + idPackage + ", Peso: " + weight + "kg, Remitente: " + sender +
                ", Destinatario: " + receiver + ", Dirección: " + address + "]";
    }
}
