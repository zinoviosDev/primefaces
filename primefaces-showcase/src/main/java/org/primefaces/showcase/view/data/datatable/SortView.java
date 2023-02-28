/*
 * The MIT License
 *
 * Copyright (c) 2009-2023 PrimeTek Informatics
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.primefaces.showcase.view.data.datatable;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.primefaces.showcase.domain.InventoryStatus;
import org.primefaces.showcase.domain.Product;
import org.primefaces.showcase.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Named("dtSortView")
@ViewScoped
public class SortView implements Serializable {

    private List<Product> products;
    private List<Product> products1;
    private List<Product> products2;
    private List<Product> products3;
    private List<SortMeta> sortBy;

    @Inject
    private ProductService service;

    @PostConstruct
    public void init() {
        products = service.getProducts(10);
        products1 = service.getProducts(10);
        products2 = service.getProducts(10);
        products3 = service.getProducts(50);
        products.add(new Product(45, 45L, "Black Watch", "Product Description", "black-watch.jpg", 12,
                "Accessories", 11, InventoryStatus.INSTOCK, 4));
        products.add(new Product(48, 4L, "Black Watch", "Product Description", "black-watch.jpg", 22,
                "Accessories", 12, InventoryStatus.INSTOCK, 4));
        products.add(new Product(48, 4L, "Black Watch", "Product Description", "black-watch.jpg", 22,
                "Accessories", 12, InventoryStatus.INSTOCK, 4));
        products.add(new Product(47, 445L, "Black Watch", "Product Description", "black-watch.jpg", 32,
                "Accessories", 13, InventoryStatus.INSTOCK, 4));
        sortProductsByNameAndCodeLong();
        sortBy = new ArrayList<>();
        sortBy.add(SortMeta.builder()
                .field("name")
                .order(SortOrder.ASCENDING)
                .build());
    }

    private void sortProductsByNameAndCodeLong() {
        Collections.sort(products, Comparator.comparing(Product::getName, Comparator.nullsFirst(String::compareTo))
                .thenComparing(Product::getCodeLong, Comparator.nullsFirst(Long::compareTo)));


    }

    public List<Product> getProducts() {

        return products;
    }

    public List<Product> getProducts1() {

        return products1;
    }

    public List<Product> getProducts2() {

        return products2;
    }

    public List<Product> getProducts3() {

        return products3;
    }

    public List<SortMeta> getSortBy() {

        return sortBy;
    }

    public void setService(ProductService service) {

        this.service = service;
    }

    public void onRowEdit(RowEditEvent<Product> event) {
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getCode()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Product> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getCode()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
