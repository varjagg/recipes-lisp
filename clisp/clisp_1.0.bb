DEPENDS="readline" 

BBCLASSEXTEND="native"

SRC_URI="hg://hg.code.sf.net/p/clisp;module=clisp;protocol=http;rev=tip;"

LICENSE="GPLv2"
LIC_FILES_CHKSUM="file://GNU-GPL;md5=b234ee4d69f5fce4486a80fdaf4a4263"

S="${WORKDIR}/clisp"

inherit autoconf pkgconfig

EXTRA_OECONF += "--ignore-absence-of-libsigsegv \
	"
