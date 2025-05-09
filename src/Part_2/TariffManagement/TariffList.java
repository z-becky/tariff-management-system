package Part_2.TariffManagement;
//-------------------------------------------------
// Assignment 3
// Question 2
// Written by: Becky Zhang (40302813) & Rojin Niknejad (40264301)
//-------------------------------------------------


import java.util.NoSuchElementException;

public class TariffList implements TariffPolicy {

    @Override
    public String toString() {
        if (head == null) {
            return "The tariff list is empty.";
        }

        String listString = "";
        listString += "TariffList:\n";

        TariffNode current = head;
        int position = 0;

        while (current != null) {
            Tariff tariff = current.getTariffs();
            listString += position + ". "
                    + "Destination: " + tariff.getDestinationCountry()
                    + ", Origin: " + tariff.getOriginCountry()
                    + ", Category: " + tariff.getProductCategory()
                    + ", Minimum Tariff: " + tariff.getMinimumTariff()+ "%\n";

            current = current.getNext();
            position++;
        }

        return listString;
    }

    @Override
    public String evaluateTrade(double proposedTariff, double minimumTariff) {

        if (proposedTariff >= minimumTariff){
            return " - Accepted.\nThe proposed tariff meets or exceeds the minimum required tariff.\n";
        }
        else if(proposedTariff < minimumTariff && proposedTariff >= minimumTariff*0.8){
            return " - Conditionally Accepted.\nProposed tariff 20% is within 20% of the required minimum tariff " + minimumTariff + "%.\n";
        }else {
            return " - Rejected.\nThe proposed tariff is more than 20% below the required tariff " + minimumTariff + "%.\n";
        }
    }

    public String evaluateRequest(String origin, String destination, String category,double tradeValue ,double proposedTariff) {
        TariffNode match = find(origin, destination, category);

        if (match != null) {
            double minimumTariff = match.getTariffs().getMinimumTariff();
            if (evaluateTrade(proposedTariff, minimumTariff).contains("Conditionally Accepted.")){
                return evaluateTrade(proposedTariff, minimumTariff) + "A surcharge of $" +
                        tradeValue*((minimumTariff-proposedTariff)/100) + " is applied.\n";
            }
            return evaluateTrade(proposedTariff, minimumTariff);
        } else {
            return " - No matching tariff policy found. Cannot evaluate request.\n";
        }
    }

    private TariffNode head;
    private int size;

    // default constructor
    public TariffList() {
    }

    // parametrized constructor
    public TariffList(TariffNode head, int size) {
        this.head = head;
        this.size = size;
    }

    // copy constructor
    public TariffList(TariffList other) {
        if (other == null) {
            throw new IllegalArgumentException("The list you are trying to copy is null!");
        }

        this.head = null;
        this.size = 0;

        // If the source list is empty, we're done
        if (other.head == null) {
            return;
        }

        // Create deep copy of the head node
        this.head = new TariffNode(other.head);
        this.size = other.size;

        // Create deep copies of remaining nodes
        TariffNode currentOriginal = other.head.getNext();
        TariffNode currentCopy = this.head;

        while (currentOriginal != null) {
            TariffNode newNode = new TariffNode(currentOriginal);
            currentCopy.setNext(newNode);
            currentCopy = newNode;
            currentOriginal = currentOriginal.getNext();
        }
    }

    // addToStart method
    public void addToStart(Tariff newTariff) {
        head = new TariffNode(newTariff, head);
        size++;
    }

    // insertAtIndex method
    public void insertAtIndex(Tariff newTariff, int index) {

        // checking if the index is valid
        try {

            if (index < 0 || index >= size) {
                throw new NoSuchElementException("Invalid index. Could not insert tariff.");
            }

            // traverse to the node before the chosen index
            TariffNode position = head;
            for (int i = 0; i < index - 1; i++) {
                position = position.getNext();
            }
            // create a new node
            position.setNext(new TariffNode(newTariff, position.getNext()));

            size++;

        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());

        }

    }

    // deleteFromIndex Method
    public void deleteFromIndex(int index) {

        //checking if the index is valid
        try {
            if (index < 0 || index >= size) {
                throw new NoSuchElementException("Invalid index. Could not delete tariff.");
            }
            // if index is 0 head is pointing to the next node
            if (index == 0) {
                head = head.getNext();
            } else {
                TariffNode position = head;
                for (int i = 0; i < index - 1; i++) {
                    position= position.getNext();
                }
                position.setNext(position.getNext().getNext());
            }
            size--;
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    // deleteFromStart method
    public void deleteFromStart() {
        // check if the list is empty
        if (head == null) {
            System.out.println("List is already empty!");
            return;
        }

        head = head.getNext();
        size--;

    }

    // ReplaceAtIndex Method
    public void replaceAtIndex(Tariff newTariff, int index) {
        // check if the index is valid
        if (index < 0 || index >= size) {
            System.out.println("Invalid index. Could not replace tariff.");
            return;
        }
        TariffNode position = head;

        // going through the linked list for the specific index

        for (int i = 0; i < index; i++) {
            position = position.getNext();

        }
        position.setTariffs(newTariff);

    }

    //find method
    public TariffNode find(String origin, String destination, String category) {
        TariffNode position = head;
        int count = 0;
        while (position != null) {
            count++;
            // get the tariff from the node
            Tariff t = position.getTariffs();
            if (t.getOriginCountry().equalsIgnoreCase(origin) && t.getDestinationCountry().equalsIgnoreCase(destination)
                    && t.getProductCategory().equalsIgnoreCase(category)) {
                System.out.println("\n--- Tariff found after " + count + " iteration(s)! ---");
                System.out.println(t + "\n");
                // if the tariff is found return it
                return position;
            }
            position = position.getNext();
        }
        // if not return null
        System.out.println("Tariff not found!");
        return null;
    }

    // contains method
    public boolean contains(String origin, String destination, String category) {

        TariffNode position = head;
        while (position != null) {

            // compare to other tariffs
            if (position.getTariffs().getOriginCountry().equals(origin) &&
                    position.getTariffs().getDestinationCountry().equals(destination) &&
                    position.getTariffs().getProductCategory().equals(category)) {
                return true;
            }
            position = position.getNext();

        }
        return false;

    }

    // equal method
    public boolean equals(TariffList other) {
        if (this == other ) return true;
        if (other == null) {
            return false;
        }
        if (this.size != other.size) {
            return false;
        }
        TariffNode position = this.head;
        TariffNode positionOther = other.head;

        while (position != null && positionOther != null) {
            if (!position.getTariffs().equals(positionOther.getTariffs())) {
                return false;

            } // comparing all nodes
            position = position.getNext();
            positionOther = positionOther.getNext();
        }
        // all nodes matched
        return true;
    }


    // inner class
    class TariffNode {

        private Tariff tariffs;
        private TariffNode next;

        // default constructor
        public TariffNode() {
            this.tariffs = null;
            this.next = null;

        }

        // parameterized constructor
        public TariffNode(Tariff tariffs, TariffNode next) {
            this.tariffs = tariffs;
            this.next = next;
        }

        // copy constructor that creates a deep copy
        public TariffNode(TariffNode other) {
            this.tariffs = new Tariff(other.tariffs.destinationCountry, other.tariffs.originCountry,
                    other.tariffs.productCategory, other.tariffs.minimumTariff);

            if (other.next != null) {
                this.next = new TariffNode(other.next);
            } else {
                this.next = null;
            }
        }

        // clone method
        public TariffNode clone() {
            Tariff clonedTariff = new Tariff(this.tariffs);
            TariffNode clonedTariffNode;

            if (this.next != null) {
                clonedTariffNode = this.next.clone();
            } else {
                clonedTariffNode = null;
            }
            return new TariffNode(clonedTariff, clonedTariffNode);
        }

        // getters and setters
        public Tariff getTariffs() {
            return tariffs;
        }

        public void setTariffs(Tariff tariffs) {
            this.tariffs = tariffs;
        }

        public TariffNode getNext() {
            return next;
        }

        public void setNext(TariffNode next) {
            this.next = next;
        }

        // equal method
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;

            TariffNode other = (TariffNode) obj;
            return this.tariffs.equals(other.tariffs) && this.next.equals(other.next);
        }


    }
}

