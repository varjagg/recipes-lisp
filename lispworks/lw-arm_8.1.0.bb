SUMMARY="LispWorks Common Lisp implementation for ARM target"

BBCLASSEXTEND="native"

DEPENDS+="qemu-native"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_DEFAULT_DEPS = "1"

inherit native

FILESEXTRAPATHS:prepend := "${THISDIR}/scripts/:"

FILES_${PN} += "  \
	${datadir}/lispworks/** \
	${bindir}/lwc \
"

# change to the actual path in your FS/container mapping of the archives
SRC_URI+="file:///lw-arm/lw81-arm-linux.tar.gz"
SRC_URI+="file:///lw-arm/qemu-libs.tar.gz"

SRC_URI+="file://lwc"

LICENSE="CLOSED"

S="${WORKDIR}"

do_configure() {
    # the license key and serial have to be set in environment variables (e.g. local.conf)
    qemu-arm -L ${S}/qemu/arm-linux-libs ${S}/lispworks-8-1-0-arm-linux --lwlicenseserial ${LW_SERIAL} --lwlicensekey ${LW_KEY}
}

lwdir="${D}/${datadir}/lispworks"

do_install:append() {
    install -d ${D}/${bindir}
    install -Dm 0755 ${S}/lwc ${D}/${bindir}/
    install -d ${lwdir}
    install -m 0644 ${S}/lispworks-8-1-0-arm-linux ${lwdir}/
    install -m 0644 ${S}/lwlicense ${lwdir}/
    cp -r ${S}/qemu ${lwdir}/
    cp -r ${S}/lib ${lwdir}/
}
