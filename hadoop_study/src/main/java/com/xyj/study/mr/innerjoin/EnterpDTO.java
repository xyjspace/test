package com.xyj.study.mr.innerjoin;

import lombok.Data;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by banma on 2017/8/17.
 */
@Data
public class EnterpDTO implements Writable {

    private String enterpId = "";

    private String enterpName = "";

    private String provinceName = "";

    private String verifyStatus = "";

    private String userId = "";

    private String userName = "";

    private String password = "";

    private String mapId = "";

    private String table = "";

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(enterpId);
        dataOutput.writeUTF(enterpName);
        dataOutput.writeUTF(provinceName);
        dataOutput.writeUTF(verifyStatus);
        dataOutput.writeUTF(userId);
        dataOutput.writeUTF(userName);
        dataOutput.writeUTF(password);
        dataOutput.writeUTF(mapId);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.enterpId = dataInput.readUTF();
        this.enterpName = dataInput.readUTF();
        this.provinceName = dataInput.readUTF();
        this.verifyStatus = dataInput.readUTF();
        this.userId = dataInput.readUTF();
        this.userName = dataInput.readUTF();
        this.password = dataInput.readUTF();
        this.mapId = dataInput.readUTF();
    }
}
