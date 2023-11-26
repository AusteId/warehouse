import lt.itakademija.exam.*;
import lt.itakademija.exam.Package;

import java.util.List;

public class MyWirehouse extends Warehouse {
    public MyWirehouse(IdGenerator clientIdGenerator, IdGenerator packageIdGenerator, int totalSpace) {
        super(clientIdGenerator, packageIdGenerator, totalSpace);
    }

    

    @Override
    public int getTotalSpace() {
        return 0;
    }

    @Override
    public int getAvailableSpace() {
        return 0;
    }

    @Override
    public int getTotalAvailableSpace() {
        return 0;
    }

    @Override
    public int getReservedSpace() {
        return 0;
    }

    @Override
    public Client registerClient(String s, int i) {
        return null;
    }

    @Override
    public Package createPackage(String s, int i) {
        return null;
    }

    @Override
    public void storePackage(Client client, Package aPackage) {

    }

    @Override
    public Client getClientById(int i) {
        return null;
    }

    @Override
    public boolean hasClientById(int i) {
        return false;
    }

    @Override
    public List<Client> findClientsBy(ClientPredicate clientPredicate) {
        return null;
    }
}
