package otus.base;

import javax.persistence.*;

/**
 * Created by svetlana on 7/3/17.
 */
@MappedSuperclass
public class DataSet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}