package com.example.schoolPaymentManagement.helper;

/**
 * @author Raha
 * @since 2023-06-22
 *
 * <p>
 *     To hold music and playlist data for transfer between server and client.
 * </p>
 */
public class EntryBean {
    private Integer fromYear;
    private Integer toYear;
    private String name;
    private String playListName;

    public EntryBean(Integer fromYear, Integer toYear, String name, String playListName) {
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.name = name;
        this.playListName = playListName;
    }

    public EntryBean(String name) {
        this.name = name;
    }

    public Integer getFromYear() {
        return fromYear;
    }

    public void setFromYear(Integer fromYear) {
        this.fromYear = fromYear;
    }

    public Integer getToYear() {
        return toYear;
    }

    public void setToYear(Integer toYear) {
        this.toYear = toYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    @Override
    public String toString() {
        return "EntryBean{" +
                "fromYear=" + fromYear +
                ", toYear=" + toYear +
                ", name='" + name + '\'' +
                ", playListName='" + playListName + '\'' +
                '}';
    }
}
