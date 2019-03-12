SUMMARY="CLISP Common Lisp implementation"

#include include/clispbuild.inc

DEPENDS="readline" 

BBCLASSEXTEND="native"

SRC_URI="hg://hg.code.sf.net/p/clisp;module=clisp;protocol=http;rev=tip;"

LICENSE="GPLv2"
LIC_FILES_CHKSUM="file://GNU-GPL;md5=6596adfdd6a87b1d04c38c2bd05de0cd"

S="${WORKDIR}/clisp"

inherit autotools pkgconfig

CONFIGUREOPTS = " --build=${BUILD_SYS} \
		  --host=${HOST_SYS} \
		  --prefix=${prefix} \
		  --exec_prefix=${exec_prefix} \
		  --srcdir=${S} \
		  --bindir=${bindir} \
		  --datadir=${datadir} \
		  --libdir=${libdir} \
		  --includedir=${includedir} \
		  --infodir=${infodir} \
		  --mandir=${mandir} \
		  --disable-silent-rules \
		  ${CONFIGUREOPT_DEPTRACK} \
		  ${@append_libtool_sysroot(d)}"

EXTRA_OECONF += "--ignore-absence-of-libsigsegv \
	"
