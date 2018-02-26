/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rakshit Shah (rakshitshah1994@gmail.com)
 */
@Entity
@Table(name = "TransactionDetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionDetails.findAll", query = "SELECT t FROM TransactionDetails t"),
    @NamedQuery(name = "TransactionDetails.findByTransactionID", query = "SELECT t FROM TransactionDetails t WHERE t.transactionID = :transactionID"),
    @NamedQuery(name = "TransactionDetails.findByStatus", query = "SELECT t FROM TransactionDetails t WHERE t.status = :status")})
public class TransactionDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TransactionID")
    private String transactionID;
    @Basic(optional = false)
    @Column(name = "Status")
    private String status;
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderId")
    @ManyToOne(optional = false)
    private OrderDetails orderID;

    public TransactionDetails() {
    }

    public TransactionDetails(String transactionID) {
        this.transactionID = transactionID;
    }

    public TransactionDetails(String transactionID, String status) {
        this.transactionID = transactionID;
        this.status = status;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderDetails getOrderID() {
        return orderID;
    }

    public void setOrderID(OrderDetails orderID) {
        this.orderID = orderID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionID != null ? transactionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransactionDetails)) {
            return false;
        }
        TransactionDetails other = (TransactionDetails) object;
        if ((this.transactionID == null && other.transactionID != null) || (this.transactionID != null && !this.transactionID.equals(other.transactionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TransactionDetails[ transactionID=" + transactionID + " ]";
    }
    
}
