package com.monster.immutability;

public final class SmsInfo {

    private final String url;

    private final Integer maxSizeInBytes;

    public SmsInfo(String url, Integer maxSizeInBytes) {
        this.url = url;
        this.maxSizeInBytes = maxSizeInBytes;
    }

    public String getUrl() {
        return url;
    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }

    public Integer getMaxSizeInBytes() {
        return maxSizeInBytes;
    }

//    public void setMaxSizeInBytes(Integer maxSizeInBytes) {
//        this.maxSizeInBytes = maxSizeInBytes;
//    }

    @Override
    public String toString() {
        return "SmsInfo{" +
                "url='" + url + '\'' +
                ", maxSizeInBytes=" + maxSizeInBytes +
                '}';
    }

    public SmsInfo update(String ulr,Integer size){
        return new SmsInfo(ulr,size);
    }

}
