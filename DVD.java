class DVD implements Comparable<DVD> {
    private String title;
    private int available;
    private int rented;

    public DVD(String title, int available, int rented) {
        this.title = title;
        this.available = available;
        this.rented = rented;
    }

    public String getTitle() {
        return title;
    }

    public int getAvailable() {
        return available;
    }

    public int getRented() {
        return rented;
    }

    public void incrementAvailable(int amount) {
        this.available += amount;
    }

    public void incrementRented() {
        this.rented++;
    }

    public void decrementAvailable(int amount) {
        this.available -= amount;
    }

    public void decrementRented() {
        this.rented--;
    }

    @Override
    public int compareTo(DVD otherDVD) {
        return this.title.compareTo(otherDVD.title);
    }

    @Override
    public String toString() {
        return String.format("%-30s%-15s%-15s", title, available, rented);
    }
}
