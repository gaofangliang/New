package com.example.administrator.myapplication.base;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/5/14 0014.
 */
@Entity
public class GreenBean  {
    @Id
    private Long id;
    @NotNull
    @Property(nameInDb = "name")
    @Unique
    private String name;
    @Generated(hash = 2050392509)
    public GreenBean(Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }
    public GreenBean( @NotNull String name) {
        this.name = name;
    }
    @Generated(hash = 1002137420)
    public GreenBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
