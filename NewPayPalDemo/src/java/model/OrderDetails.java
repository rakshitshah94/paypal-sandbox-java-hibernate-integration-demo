/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rakshit shah (rakshitshah1994@gmail.com)
 */
@Entity
@Table(name = "OrderDetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDetails.findAll", query = "SELECT o FROM OrderDetails o"),
    @NamedQuery(name = "OrderDetails.findByOrderId", query = "SELECT o FROM OrderDetails o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "OrderDetails.findByUserid", query = "SELECT o FROM OrderDetails o WHERE o.userid = :userid"),
    @NamedQuery(name = "OrderDetails.findByQty", query = "SELECT o FROM OrderDetails o WHERE o.qty = :qty"),
    @NamedQuery(name = "OrderDetails.findByAmount", query = "SELECT o FROM OrderDetails o WHERE o.amount = :amount"),
    @NamedQuery(name = "OrderDetails.findByTstatus", query = "SELECT o FROM OrderDetails o WHERE o.tstatus = :tstatus")})
public class OrderDetails implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderID")
    private Collection<TransactionDetails> transactionDetailsCollection;
    private static final long serialVersionUID = 1L;
    @Id  @GeneratedValue
    @Basic(optional = false)
    @Column(name = "OrderId")
    private Integer orderId;
    @Column(name = "userid")
    private Integer userid;
    @Column(name = "qty")
    private Integer qty;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Column(name = "tstatus")
    private String tstatus;
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    @ManyToOne
    private ProductTable pid;

    public OrderDetails() {
    }

    public OrderDetails(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTstatus() {
        return tstatus;
    }

    public void setTstatus(String tstatus) {
        this.tstatus = tstatus;
    }

    public ProductTable getPid() {
        return pid;
    }

    public void setPid(ProductTable pid) {
        this.pid = pid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetails)) {
            return false;
        }
        OrderDetails other = (OrderDetails) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.OrderDetails[ orderId=" + orderId + " ]";
    }

    @XmlTransient
    public Collection<TransactionDetails> getTransactionDetailsCollection() {
        return transactionDetailsCollection;
    }

    public void setTransactionDetailsCollection(Collection<TransactionDetails> transactionDetailsCollection) {
        this.transactionDetailsCollection = transactionDetailsCollection;
    }
    
}
