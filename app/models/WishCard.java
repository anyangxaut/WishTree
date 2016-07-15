package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anyang on 2016/7/13.
 */
@Entity
@Table(name="wishcard")
public class WishCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id")
    public int _id;
    @Column(name = "_name")
    public String _name;

}
