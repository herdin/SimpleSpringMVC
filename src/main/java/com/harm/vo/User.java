package com.harm.vo;

public class User {
    String id;
    String name;
    String nickname;
    String anagram;
    public static class Builder {
        String id;
        String name;
        String nickname;
        String anagram;

        public Builder id(String id) { this.id = id; return this;}
        public Builder name(String name) { this.name = name; return this; }
        public Builder nickname(String nickname) { this.nickname = nickname; return this; }
        public Builder anagram(String anagram) { this.anagram = anagram; return this; }
        public User build() {
            return new User(this.id, this.name, this.nickname, this.anagram);
        }
    }
    private User(String id, String name, String nickname, String anagram) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.anagram = anagram;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAnagram() {
        return anagram;
    }
}
