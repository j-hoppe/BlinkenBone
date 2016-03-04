/*
 * Automatically generated by jrpcgen 1.0.7 on 20.02.16 08:08
 * jrpcgen is part of the "Remote Tea" ONC/RPC package for Java
 * See http://remotetea.sourceforge.net for details
 */
package blinkenbone.rpcgen;
import org.acplt.oncrpc.*;
import java.io.IOException;

import java.net.InetAddress;

import org.acplt.oncrpc.server.*;

/**
 */
public abstract class rpc_blinkenlight_apiServerStub extends OncRpcServerStub implements OncRpcDispatchable {

    public rpc_blinkenlight_apiServerStub()
           throws OncRpcException, IOException {
        this(0);
    }

    public rpc_blinkenlight_apiServerStub(int port)
           throws OncRpcException, IOException {
        this(null, port);
    }

    public rpc_blinkenlight_apiServerStub(InetAddress bindAddr, int port)
           throws OncRpcException, IOException {
        info = new OncRpcServerTransportRegistrationInfo [] {
            new OncRpcServerTransportRegistrationInfo(rpc_blinkenlight_api.BLINKENLIGHTD, 1),
        };
        transports = new OncRpcServerTransport [] {
            new OncRpcUdpServerTransport(this, bindAddr, port, info, 32768),
            new OncRpcTcpServerTransport(this, bindAddr, port, info, 32768)
        };
    }

    public void dispatchOncRpcCall(OncRpcCallInformation call, int program, int version, int procedure)
           throws OncRpcException, IOException {
        if ( version == 1 ) {
            switch ( procedure ) {
            case 1: {
                call.retrieveCall(XdrVoid.XDR_VOID);
                rpc_blinkenlight_api_getinfo_res result$ = RPC_BLINKENLIGHT_API_GETINFO_1();
                call.reply(result$);
                break;
            }
            case 2: {
                XdrInt args$ = new XdrInt();
                call.retrieveCall(args$);
                rpc_blinkenlight_api_getpanelinfo_res result$ = RPC_BLINKENLIGHT_API_GETPANELINFO_1(args$.intValue());
                call.reply(result$);
                break;
            }
            case 3: {
                class XdrAble$ implements XdrAble {
                    public int arg1;
                    public int arg2;
                    public void xdrEncode(XdrEncodingStream xdr)
                        throws OncRpcException, IOException {
                    }
                    public void xdrDecode(XdrDecodingStream xdr)
                        throws OncRpcException, IOException {
                        arg1 = xdr.xdrDecodeInt();
                        arg2 = xdr.xdrDecodeInt();
                    }
                };
                XdrAble$ args$ = new XdrAble$();
                call.retrieveCall(args$);
                rpc_blinkenlight_api_getcontrolinfo_res result$ = RPC_BLINKENLIGHT_API_GETCONTROLINFO_1(args$.arg1, args$.arg2);
                call.reply(result$);
                break;
            }
            case 4: {
                class XdrAble$ implements XdrAble {
                    public int arg1;
                    public rpc_blinkenlight_api_controlvalues_struct valuelist;
                    public void xdrEncode(XdrEncodingStream xdr)
                        throws OncRpcException, IOException {
                    }
                    public void xdrDecode(XdrDecodingStream xdr)
                        throws OncRpcException, IOException {
                        arg1 = xdr.xdrDecodeInt();
                        valuelist = new rpc_blinkenlight_api_controlvalues_struct(xdr);
                    }
                };
                XdrAble$ args$ = new XdrAble$();
                call.retrieveCall(args$);
                rpc_blinkenlight_api_setpanel_controlvalues_res result$ = RPC_BLINKENLIGHT_API_SETPANEL_CONTROLVALUES_1(args$.arg1, args$.valuelist);
                call.reply(result$);
                break;
            }
            case 5: {
                XdrInt args$ = new XdrInt();
                call.retrieveCall(args$);
                rpc_blinkenlight_api_controlvalues_struct result$ = RPC_BLINKENLIGHT_API_GETPANEL_CONTROLVALUES_1(args$.intValue());
                call.reply(result$);
                break;
            }
            case 100: {
                rpc_param_cmd_get_struct args$ = new rpc_param_cmd_get_struct();
                call.retrieveCall(args$);
                rpc_param_result_struct result$ = RPC_PARAM_GET_1(args$);
                call.reply(result$);
                break;
            }
            case 101: {
                rpc_param_cmd_set_struct args$ = new rpc_param_cmd_set_struct();
                call.retrieveCall(args$);
                rpc_param_result_struct result$ = RPC_PARAM_SET_1(args$);
                call.reply(result$);
                break;
            }
            case 1000: {
                rpc_test_data_struct args$ = new rpc_test_data_struct();
                call.retrieveCall(args$);
                rpc_test_cmdstatus_struct result$ = RPC_TEST_DATA_TO_SERVER_1(args$);
                call.reply(result$);
                break;
            }
            case 1001: {
                rpc_test_cmdstatus_struct args$ = new rpc_test_cmdstatus_struct();
                call.retrieveCall(args$);
                rpc_test_data_struct result$ = RPC_TEST_DATA_FROM_SERVER_1(args$);
                call.reply(result$);
                break;
            }
            default:
                call.failProcedureUnavailable();
            }
        } else {
            call.failProgramUnavailable();
        }
    }

    public abstract rpc_blinkenlight_api_getinfo_res RPC_BLINKENLIGHT_API_GETINFO_1();

    public abstract rpc_blinkenlight_api_getpanelinfo_res RPC_BLINKENLIGHT_API_GETPANELINFO_1(int arg1);

    public abstract rpc_blinkenlight_api_getcontrolinfo_res RPC_BLINKENLIGHT_API_GETCONTROLINFO_1(int arg1, int arg2);

    public abstract rpc_blinkenlight_api_setpanel_controlvalues_res RPC_BLINKENLIGHT_API_SETPANEL_CONTROLVALUES_1(int arg1, rpc_blinkenlight_api_controlvalues_struct valuelist);

    public abstract rpc_blinkenlight_api_controlvalues_struct RPC_BLINKENLIGHT_API_GETPANEL_CONTROLVALUES_1(int arg1);

    public abstract rpc_param_result_struct RPC_PARAM_GET_1(rpc_param_cmd_get_struct cmd_get);

    public abstract rpc_param_result_struct RPC_PARAM_SET_1(rpc_param_cmd_set_struct cmd_set);

    public abstract rpc_test_cmdstatus_struct RPC_TEST_DATA_TO_SERVER_1(rpc_test_data_struct data);

    public abstract rpc_test_data_struct RPC_TEST_DATA_FROM_SERVER_1(rpc_test_cmdstatus_struct data);

}
// End of rpc_blinkenlight_apiServerStub.java
