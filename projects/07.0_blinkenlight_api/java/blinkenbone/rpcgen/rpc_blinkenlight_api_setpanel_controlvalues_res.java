/*
 * Automatically generated by jrpcgen 1.0.7 on 20.02.16 08:08
 * jrpcgen is part of the "Remote Tea" ONC/RPC package for Java
 * See http://remotetea.sourceforge.net for details
 */
package blinkenbone.rpcgen;
import org.acplt.oncrpc.*;
import java.io.IOException;

public class rpc_blinkenlight_api_setpanel_controlvalues_res implements XdrAble {
    public int error_code;

    public rpc_blinkenlight_api_setpanel_controlvalues_res() {
    }

    public rpc_blinkenlight_api_setpanel_controlvalues_res(XdrDecodingStream xdr)
           throws OncRpcException, IOException {
        xdrDecode(xdr);
    }

    public void xdrEncode(XdrEncodingStream xdr)
           throws OncRpcException, IOException {
        xdr.xdrEncodeInt(error_code);
    }

    public void xdrDecode(XdrDecodingStream xdr)
           throws OncRpcException, IOException {
        error_code = xdr.xdrDecodeInt();
    }

}
// End of rpc_blinkenlight_api_setpanel_controlvalues_res.java
