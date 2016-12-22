package auth;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.security.SecureRandom;
import java.util.Random;

@Entity
@Table(name="Users")
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String providerIP;

    @NotNull
    private byte[] seed;

    @NotNull
    private int backupQrCode;

    protected User() {} //JPA needs this

    public User(String name, String providerIP) {
        this.name = name;
        this.providerIP = providerIP;
        this.seed = generateSeed();
        this.backupQrCode = generateBackupQrCode();
    }

    /**
     * Generates a random number
     * @return
     */
    private byte[] generateSeed() {
        Random randomGenerator = new SecureRandom();
        byte[] key = new byte[16];
        randomGenerator.nextBytes(key);
        return key;
    }

    private int generateBackupQrCode() {
        return 0;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProviderIP() {
        return providerIP;
    }

    public byte[] getSeed() {
        return seed;
    }

    public int getBackupQrCode() {
        return backupQrCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", providerIP='" + providerIP + '\'' +
                ", seed=" + seed +
                ", backupQrCode=" + backupQrCode +
                '}';
    }
}
