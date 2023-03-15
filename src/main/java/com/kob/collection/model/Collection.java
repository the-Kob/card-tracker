package com.kob.collection.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table
public class Collection {
    @Id
    @SequenceGenerator(
            name = "collection_id_sequence",
            sequenceName = "collection_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "collection_id_sequence"
    )

    private Integer id;
    private String name;
    private LocalDate releaseDate;
    private String series;
    private boolean complete;
    private String coverURL;

    public Collection(Integer id, String name, LocalDate releaseDate, String series, boolean complete, String coverURL) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.series = series;
        this.complete = complete;
        this.coverURL = coverURL;
    }

    public Collection() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collection that = (Collection) o;
        return complete == that.complete && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(series, that.series) && Objects.equals(coverURL, that.coverURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseDate, series, complete, coverURL);
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", series='" + series + '\'' +
                ", complete=" + complete +
                ", coverURL='" + coverURL + '\'' +
                '}';
    }
}
