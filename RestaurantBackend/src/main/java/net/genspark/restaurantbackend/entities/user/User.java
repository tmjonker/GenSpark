package net.genspark.restaurantbackend.entities.user;

import net.genspark.restaurantbackend.entities.address.Address;
import net.genspark.restaurantbackend.entities.purchase.Purchase;
import net.genspark.restaurantbackend.entities.reward.Reward;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String date;

    @ManyToMany
    @JoinTable(
            name = "users_purchases",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "purchase_id")
    )
    private List<Purchase> purchases;

    @ManyToMany
    @JoinTable(
            name = "users_rewards",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "reward_id")
    )
    private List<Reward> rewards;

    @OneToMany
    @JoinTable(
            name = "users_addresses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addresses;

    public User() {}

    public User(String username, String password, String email, String date) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.date = date;
    }

    public void addAddress(Address address) {

        addresses.add(address);
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addPurchase(Purchase purchase) {

        purchases.add(purchase);
    }

    public List<Purchase> getPurchases() {

        return purchases;
    }

    public void addReward(Reward reward) {

        rewards.add(reward);
    }

    public List<Reward> getRewards() {

        return rewards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", date='" + date + '\'' +
                ", purchases=" + purchases +
                ", rewards=" + rewards +
                '}';
    }
}
