/*
 * Automatically generated by jrpcgen 1.0.7 on 20.02.16 08:08
 * jrpcgen is part of the "Remote Tea" ONC/RPC package for Java
 * See http://remotetea.sourceforge.net for details
 */
package blinkenbone.rpcgen;
import org.acplt.oncrpc.*;
import java.io.IOException;

public class rpc_test_data_struct implements XdrAble {
    public int fixdata1;
    public int fixdata2;
    public byte [] vardata;

    public rpc_test_data_struct() {
    }

    public rpc_test_data_struct(XdrDecodingStream xdr)
           throws OncRpcException, IOException {
        xdrDecode(xdr);
    }

    public void xdrEncode(XdrEncodingStream xdr)
           throws OncRpcException, IOException {
        xdr.xdrEncodeInt(fixdata1);
        xdr.xdrEncodeInt(fixdata2);
        xdr.xdrEncodeByteVector(vardata);
    }

    public void xdrDecode(XdrDecodingStream xdr)
           throws OncRpcException, IOException {
        fixdata1 = xdr.xdrDecodeInt();
        fixdata2 = xdr.xdrDecodeInt();
        vardata = xdr.xdrDecodeByteVector();
    }

}
// End of rpc_test_data_struct.java
