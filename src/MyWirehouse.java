import lt.itakademija.exam.*;
import lt.itakademija.exam.Package;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyWirehouse extends Warehouse {
    public MyWirehouse(IdGenerator clientIdGenerator, IdGenerator packageIdGenerator, int totalSpace) {

        super(clientIdGenerator, packageIdGenerator, totalSpace);

        if (totalSpace <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private ArrayList<Package> packages = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();

    @Override
    public int getTotalSpace() {
        return totalSpace;
    }

    @Override
    public int getAvailableSpace() {

        int availableSpace = totalSpace - getReservedSpace();
        return availableSpace;
    }

    @Override
    public int getTotalAvailableSpace() {
        int totalClientsPackagesSpaces = packages.stream().mapToInt(a -> a.getSpace()).sum();
        int totalAvailableSpace = totalSpace - totalClientsPackagesSpaces;
        return totalAvailableSpace;

    }

    @Override
    public int getReservedSpace() {

        int reservedSpace = clients.stream().mapToInt(a -> a.getReservedSpace()).sum();
        return reservedSpace;
    }

    @Override
    public Client registerClient(String s, int i) {

        if (getAvailableSpace() < i) {
            throw new InsufficientSpaceException("Not enough space in Warehouse");
        }

        if (i <= 0) {
            throw new IllegalArgumentException();
        }

        Client client = new Client(clientIdGenerator.generateId(), s, i);
        clients.add(client);
        return client;
    }

    @Override
    public Package createPackage(String s, int i) {

        if (i <= 0) {
            throw new IllegalArgumentException("Insert correct space");
        }

        Package pac = new Package(packageIdGenerator.generateId(), s, i);
        packages.add(pac);
        return pac;

    }

    @Override
    public void storePackage(Client client, Package aPackage) {

//        if (aPackage.getSpace() < client.getReservedSpace()) {
//            throw new InsufficientSpaceException("You don`t have enough space in your reserve");
//        }

        if (!(client.getReservedSpace() >= aPackage.getSpace())) {
            throw new InsufficientSpaceException("Insufficient space");
        }

        client.addPackage(aPackage);

    }

    @Override
    public Client getClientById(int i) {

        Client client = clients.stream().filter(a -> a.getId() == i).findFirst().orElse(null);
        return client;
    }

    @Override
    public boolean hasClientById(int i) {

//        return clients.stream().filter(a -> a.getId() == i).findFirst().isPresent();
        return clients.stream().anyMatch(a -> a.getId() == i);
    }

    @Override
    public List<Client> findClientsBy(ClientPredicate clientPredicate) {

        return  clients.stream().filter(a -> clientPredicate.test(a)).collect(Collectors.toList());
    }
}
