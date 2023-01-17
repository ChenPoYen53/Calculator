package com.example.calculator.Database2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Unit")
public class MainData {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "measure")
    private String measure;
    @ColumnInfo(name = "from_value")
    private String from_value;
    @ColumnInfo(name = "from_abbr")
    private String from_abbr;
    @ColumnInfo(name = "from_system")
    private String from_system;
    @ColumnInfo(name = "from_measure")
    private String from_measure;
    @ColumnInfo(name = "from_singular")
    private String from_singular;
    @ColumnInfo(name = "from_plural")
    private String from_plural;
    @ColumnInfo(name = "to_abbr")
    private String to_abbr;
    @ColumnInfo(name = "to_system")
    private String to_system;
    @ColumnInfo(name = "to_measure")
    private String to_measure;
    @ColumnInfo(name = "to_singular")
    private String to_singular;
    @ColumnInfo(name = "to_plural")
    private String to_plural;
    @ColumnInfo(name = "value")
    private double value;
    @ColumnInfo(name = "result")
    private String result;

    public String getTo_measure() {
        return to_measure;
    }

    public void setTo_measure(String to_measure) {
        this.to_measure = to_measure;
    }

    public String getFrom_value() {
        return from_value;
    }

    public void setFrom_value(String from_value) {
        this.from_value = from_value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getFrom_abbr() {
        return from_abbr;
    }

    public void setFrom_abbr(String from_abbr) {
        this.from_abbr = from_abbr;
    }

    public String getFrom_system() {
        return from_system;
    }

    public void setFrom_system(String from_system) {
        this.from_system = from_system;
    }

    public String getFrom_measure() {
        return from_measure;
    }

    public void setFrom_measure(String from_measure) {
        this.from_measure = from_measure;
    }

    public String getFrom_singular() {
        return from_singular;
    }

    public void setFrom_singular(String from_singular) {
        this.from_singular = from_singular;
    }

    public String getFrom_plural() {
        return from_plural;
    }

    public void setFrom_plural(String from_plural) {
        this.from_plural = from_plural;
    }

    public String getTo_abbr() {
        return to_abbr;
    }

    public void setTo_abbr(String to_abbr) {
        this.to_abbr = to_abbr;
    }

    public String getTo_singular() {
        return to_singular;
    }

    public void setTo_singular(String to_singular) {
        this.to_singular = to_singular;
    }

    public String getTo_plural() {
        return to_plural;
    }

    public void setTo_plural(String to_plural) {
        this.to_plural = to_plural;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTo_system() {
        return to_system;
    }

    public void setTo_system(String to_system) {
        this.to_system = to_system;
    }
}
