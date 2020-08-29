package com.common.base.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.content.AbstractContentBody;
import org.apache.http.util.Args;

/**
 * 文件上传 自定义 FileBody 支持文件流上传
 * @author zhao_li
 * @ClassName: MyFileBody
 */
public class MyFileBody extends AbstractContentBody {

    private final InputStream inputStream;
    private final ContentType contentType;
    private final String filename;

    public MyFileBody(InputStream inputStream,String fileName) {
    	super(ContentType.DEFAULT_BINARY);
    	this.inputStream=inputStream;
        this.filename = fileName;
        this.contentType=ContentType.DEFAULT_BINARY;
    }

    public InputStream getInputStream() throws IOException {
        return this.inputStream;
    }

    public void writeTo(final OutputStream out) throws IOException {
        Args.notNull(out, "Output stream");
        try {
            final byte[] tmp = new byte[4096];
            int l;
            while ((l = inputStream.read(tmp)) != -1) {
                out.write(tmp, 0, l);
            }
            out.flush();
        } finally {
            //inputStream.close();
        }
    }

    public String getTransferEncoding() {
        return MIME.ENC_BINARY;
    }

    public long getContentLength() {
        try {
			return this.inputStream.available();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
    }

    public String getFilename() {
        return filename;
    }
}
