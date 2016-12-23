package auth;

import com.google.common.io.BaseEncoding;
import org.apache.commons.codec.binary.Hex;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.security.SecureRandom;
import java.util.Arrays;
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
    private String seed;

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
     * Generates a random byte[],
     * converts it to hex and then encodes it as base32
     * Used as shared secret key and seed for totp
     * @return Base32 encoded hex-String of a random seed
     */
    private String generateSeed() {
        Random randomGenerator = new SecureRandom();
        byte[] key = new byte[16];
        randomGenerator.nextBytes(key);
        String byteToString = Arrays.toString(key);
        System.out.println("THE SEED TOSTRING: "+byteToString);
        String byteAsHex = Hex.encodeHexString(key);
        System.out.println("THE SEED byteAsHex: "+byteAsHex);
        String hexAsBase32 = BaseEncoding.base32().encode(byteAsHex.getBytes());
        System.out.println("THE SEED hexAsBase32: "+hexAsBase32);
        return hexAsBase32;
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

    public String getSeed() {
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
