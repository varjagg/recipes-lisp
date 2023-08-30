SUMMARY="LispWorks Common Lisp implementation for ARM"

BBCLASSEXTEND="native"

DEPENDS+="qemu-native"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_DEFAULT_DEPS = "1"

inherit native

FILESEXTRAPATHS_prepend := "${THISDIR}/scripts/:"

FILES_${PN} += "  \
	${datadir}/lispworks/** \
	${bindir}/lwc \
"

# change to the actual path in your FS/container mapping of the archives
SRC_URI+="file:///lispworks/lw80-arm-linux.tar.gz"
SRC_URI+="file:///lispworks/qemu-libs.tar.gz"
SRC_URI+="file://lwc"

LICENSE="CLOSED"

S="${WORKDIR}"

inherit native

do_configure() {
    # the license key and serial have to be set in environment variables (e.g. local.conf)
    qemu-arm -L ${S}/qemu/arm-linux-libs ${S}/lispworks-8-0-0-arm-linux --lwlicenseserial ${LW_S} --lwlicensekey ${LW_K}
}

do_install_append() {
    install -d ${D}/${bindir}
    install -Dm 0755 ${S}/lwc ${D}/${bindir}/
    install -d ${D}/${datadir}/lispworks
    install -m 0755 ${S}/lispworks-8-0-0-arm-linux ${D}/${datadir}/lispworks/
    install -m 0644 ${S}/lwlicense ${D}/${datadir}/lispworks/
    cp -r ${S}/qemu ${D}/${datadir}/lispworks/
    cp -r ${S}/lib ${D}/${datadir}/lispworks/
}
