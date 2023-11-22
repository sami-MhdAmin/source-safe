package com.example.sourceSafeMaven;

import jakarta.persistence.*;

@Entity
public class Version {

    @Id
    @SequenceGenerator(name = "version_sequence", sequenceName = "version_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "version_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private File file;

    @Column(name = "file_name")
    private String fileName;

    @Lob
    @Column(name = "file_content", columnDefinition = "BLOB")
    private byte[] fileContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
