package org.primefaces.showcase.view.data.datatable;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.SortOrder;
import org.primefaces.showcase.domain.Item;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Named("dtCustomView")
@ViewScoped
public class CustomView implements Serializable  {

    private List<Item> list;

    private DataTable table;

    @PostConstruct
    public void init() {
        list = new ArrayList<>();
        list.add(new Item(1, "Category 1", "Item 1", 10.0, 2));
        list.add(new Item(2, "Category 1", "Item 2", 15.0, 1));
        list.add(new Item(3, "Category 2", "Item 3", 20.0, 3));
        list.add(new Item(4, "Category 2", "Item 4", 5.0, 5));
        list.add(new Item(5, "Category 3", "Item 5", 8.0, 4));
        list.add(new Item(6, "Category 3", "Item 6", 12.0, 2));
    }

    // Getter and Setter for list
    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }

    public int categorySort(Object cat1, Object cat2) {
        String category1 = (String)cat1;
        String category2 = (String)cat2;
        FacesContext context = FacesContext.getCurrentInstance();
        String sortField = context.getExternalContext().getRequestParameterMap().get("form:datatableId_sortKey");
        String sortOrder = context.getExternalContext().getRequestParameterMap().get("form:datatableId_sortDir");

        if (sortField != null && sortField.contains("itemcategorycolumnid")) {
            if (sortOrder != null && Integer.valueOf(sortOrder) < 0 ) {
                // Tri décroissant
                return category2.compareTo(category1);
            } else {
                // Tri croissant
                return category1.compareTo(category2);
            }
        } else {
            // La colonne n'est pas triée par 'Category', retourner 0 pour ne pas modifier l'ordre
            return 0;
        }
    }

    public DataTable getTable() {
        return table;
    }

    public void setTable(DataTable table) {
        this.table = table;
    }
}
