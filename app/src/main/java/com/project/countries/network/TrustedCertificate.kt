package com.project.countries.network;

import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

/**
 * Created by Geeta on 04/02/19.
 */
class TrustedCertificate : X509TrustManager {
    @Throws(CertificateException::class)
    override fun checkClientTrusted(chain: Array<X509Certificate>,
                                    authType: String) {
    }

    @Throws(CertificateException::class)
    override fun checkServerTrusted(chain: Array<X509Certificate>,
                                    authType: String) {
    }

    override fun getAcceptedIssuers(): Array<X509Certificate?> {
        return arrayOfNulls(0)
    }
}