package com.geekbrains.geekmarketwinter.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @ManyToOne
    @NotNull(message = "категория не выбрана")
    @JoinColumn(name = "category_id")
    public Category category;

    @Column(name = "vendor_code")
    @NotNull(message = "не может быть пустым")
    @Pattern(regexp = "([0-9]{1,})", message = "недопустимый символ")
    @Size(min = 8, max = 8, message = "требуется 8 числовых символов")
    public String vendorCode;

    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "product")
    public List<ProductImage> images;

    @Column(name = "title")
    @NotNull(message = "не может быть пустым")
    @Size(min = 5, max = 250, message = "требуется минимум 5 символов")
    public String title;

    @Column(name = "short_description")
    public String shortDescription;

    @Column(name = "full_description")
    public String fullDescription;

    @Column(name = "price")
    @NotNull(message = "не может быть пустым")
    @DecimalMin(value = "0.01", message = "минимальное значение 0")
    @Digits(integer = 10, fraction = 2)
    public double price;

    @JsonIgnore
    @Column(name = "create_at")
    @CreationTimestamp
    public LocalDateTime createAt;

    @JsonIgnore
    @Column(name = "update_at")
    @UpdateTimestamp
    public LocalDateTime updateAt;

    public void addImage(ProductImage productImage) {
        if (images == null) {
            images = new ArrayList<>();
        }
        images.add(productImage);
    }

    @Override
    public String toString() {
        return "Product title = '" + title + "'";
    }

    public Product() {
    }

    public Product(Long id, Category category, String vendorCode, String title, String shortDescription, String fullDescription, double price) {
        this.id = id;
        this.category = category;
        this.vendorCode = vendorCode;
        this.title = title;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.price = price;
//        this.createAt = createAt;
//        this.updateAt = updateAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
