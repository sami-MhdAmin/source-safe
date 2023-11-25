package com.example.sourceSafeMaven.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vers")
public class Version {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private File file;

    @Column(name = "file_name")
    private String fileName;

//    @Lob
//    @Column(name = "file_content", columnDefinition = "BLOB")
//    private byte[] fileContent;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public File getFile() {
//        return file;
//    }
//
//    public void setFile(File file) {
//        this.file = file;
//    }
//
//
//    public String getFileName() {
//        return fileName;
//    }
//
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }
//
//    public byte[] getFileContent() {
//        return fileContent;
//    }
//
//    public void setFileContent(byte[] fileContent) {
//        this.fileContent = fileContent;
//    }
//    public void setUser(User user) {
//        this.user = user;
//    }
}
