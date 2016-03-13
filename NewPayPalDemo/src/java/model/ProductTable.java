/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nishant Singh
 */
@Entity
@Table(name = "ProductTable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductTable.findAll", query = "SELECT p FROM ProductTable p"),
    @NamedQuery(name = "ProductTable.findByPid", query = "SELECT p FROM ProductTable p WHERE p.pid = :pid"),
    @NamedQuery(name = "ProductTable.findByPname", query = "SELECT p FROM ProductTable p WHERE p.pname = :pname"),
    @NamedQuery(name = "ProductTable.findByPrate", query = "SELECT p FROM ProductTable p WHERE p.prate = :prate")})
public class ProductTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pid")
    private Integer pid;
    @Column(name = "pname")
    private String pname;
    @Column(name = "prate")
    private String prate;
    @OneToMany(mappedBy = "pid")
    private Collection<OrderDetails> orderDetailsCollection;

    public ProductTable() {
    }

    public ProductTable(Integer pid) {
        this.pid = pid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrate() {
        return prate;
    }

    public void setPrate(String prate) {
        this.prate = prate;
    }

    @XmlTransient
    public Collection<OrderDetails> getOrderDetailsCollection() {
        return orderDetailsCollection;
    }

    public void setOrderDetailsCollection(Collection<OrderDetails> orderDetailsCollection) {
        this.orderDetailsCollection = orderDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pid != null ? pid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductTable)) {
            return false;
        }
        ProductTable other = (ProductTable) object;
        if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ProductTable[ pid=" + pid + " ]";
    }
    
}
