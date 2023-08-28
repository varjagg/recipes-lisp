SUMMARY="LispWorks Common Lisp implementation for ARM"

BBCLASSEXTEND="native"

DEPENDS+="qemu-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/scripts/:"

# change to the actual path in your FS/container mapping
SRC_URI+="file:///lispworks/lw80-arm-linux.tar.gz"
SRC_URI+="file:///lispworks/qemu-libs.tar.gz"
SRC_URI+="file://lwc"

LICENSE="CLOSED"

S="${WORKDIR}"

inherit qemu

do_configure() {
    # the license key and serial have to be set in environment variables
    qemu-arm -L ${S}/qemu/arm-linux-libs ${S}/lw80-arm-linux/lispworks-8-0-0-arm-linux --lwlicenseserial ${LW_SERIAL} --lwlicensekey ${LW_KEY}
}

do_compile() {
}

do_install_append() {
}