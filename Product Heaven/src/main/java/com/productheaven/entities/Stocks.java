package com.productheaven.entities;

import javax.persistence.*;

/**
 * Created by Imran on 2/5/2020.
 */
@Entity
@Table(name = "stocks", schema = "product_heaven", catalog = "")
public class Stocks {
    private long id;
    private Integer numberOfProducts;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "number_of_products")
    public Integer getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(Integer numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stocks that = (Stocks) o;

        if (id != that.id) return false;
        if (numberOfProducts != null ? !numberOfProducts.equals(that.numberOfProducts) : that.numberOfProducts != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (numberOfProducts != null ? numberOfProducts.hashCode() : 0);
        return result;
    }
}
