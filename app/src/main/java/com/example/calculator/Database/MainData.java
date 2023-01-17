package com.example.calculator.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "currency")
public class MainData
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "currency1")
    private String currency1;

    @ColumnInfo(name = "result1")
    private String result1;

    @ColumnInfo(name = "currency2")
    private String currency2;

    @ColumnInfo(name = "result2")
    private String result2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrency1() {
        return currency1;
    }

    public void setCurrency1(String currency1) {
        this.currency1 = currency1;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getCurrency2() {
        return currency2;
    }

    public void setCurrency2(String currency2) {
        this.currency2 = currency2;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }
}
