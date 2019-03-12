DEPENDS="readline" 

BBCLASSEXTEND="native"

SRC_URI="hg://hg.code.sf.net/p/clisp;module=clisp;protocol=http;rev=tip;"

LICENSE="GPLv2"
LIC_FILES_CHKSUM="file://GNU-GPL;md5=6596adfdd6a87b1d04c38c2bd05de0cd"

S="${WORKDIR}/clisp"

inherit autotools pkgconfig

EXTRA_OECONF += "--ignore-absence-of-libsigsegv \
	"
