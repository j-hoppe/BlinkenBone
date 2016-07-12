/*  iSBC80-10.c: Intel iSBC 80/10 Processor simulator

    Copyright (c) 2010, William A. Beech

        Permission is hereby granted, free of charge, to any person obtaining a
        copy of this software and associated documentation files (the "Software"),
        to deal in the Software without restriction, including without limitation
        the rights to use, copy, modify, merge, publish, distribute, sublicense,
        and/or sell copies of the Software, and to permit persons to whom the
        Software is furnished to do so, subject to the following conditions:

        The above copyright notice and this permission notice shall be included in
        all copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL
        WILLIAM A. BEECH BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
        IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
        CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

        Except as contained in this notice, the name of William A. Beech shall not be
        used in advertising or otherwise to promote the sale, use or other dealings
        in this Software without prior written authorization from William A. Beech.

    MODIFICATIONS:

        ?? ??? 10 - Original file.
        24 Apr 15 -- Modified to use simh_debug

    NOTES:

        This software was written by Bill Beech, Dec 2010, to allow emulation of Multibus
        Computer Systems.
*/

#include "system_defs.h"

/* function prototypes */

uint8 get_mbyte(uint16 addr);
uint16 get_mword(uint16 addr);
void put_mbyte(uint16 addr, uint8 val);
void put_mword(uint16 addr, uint16 val);
t_stat SBC_reset (DEVICE *dptr);

/* external function prototypes */

extern t_stat i8080_reset (DEVICE *dptr);   /* reset the 8080 emulator */
extern uint8 multibus_get_mbyte(uint16 addr);
extern void  multibus_put_mbyte(uint16 addr, uint8 val);
extern uint8 EPROM_get_mbyte(uint16 addr);
extern uint8 RAM_get_mbyte(uint16 addr);
extern void RAM_put_mbyte(uint16 addr, uint8 val);
extern UNIT i8255_unit[];
extern UNIT EPROM_unit;
extern UNIT RAM_unit;
extern t_stat i8255_reset (DEVICE *dptr, uint16 base, uint8 devnum);
extern t_stat i8251_reset (DEVICE *dptr, uint16 base, uint8 devnum);
extern t_stat pata_reset (DEVICE *dptr, uint16 base);
extern t_stat EPROM_reset (DEVICE *dptr, uint16 size);
extern t_stat RAM_reset (DEVICE *dptr, uint16 base, uint16 size);

/*  SBC reset routine */

t_stat SBC_reset (DEVICE *dptr)
{    
    sim_printf("Initializing iSBC-80/10:\n");
    i8080_reset (NULL);
    i8255_reset (NULL, I8255_BASE_0, 0);
    i8255_reset (NULL, I8255_BASE_1, 1);
    i8251_reset (NULL, I8251_BASE, 0);
    EPROM_reset (NULL, ROM_SIZE);
    RAM_reset (NULL, RAM_BASE, RAM_SIZE);
    return SCPE_OK;
}

/*  get a byte from memory - handle RAM, ROM, I/O, and Multibus memory */

uint8 get_mbyte(uint16 addr)
{
    /* if local EPROM handle it */
	if (i8255_unit[0].u5 & 0x01) {
		if ((addr >= EPROM_unit.u3) && ((uint16)addr < (EPROM_unit.u3 + EPROM_unit.capac))) {
			return EPROM_get_mbyte(addr);
		}
    } /* if local RAM handle it */
	if (i8255_unit[0].u5 & 0x02) {
		if ((addr >= RAM_unit.u3) && ((uint16)addr < (RAM_unit.u3 + RAM_unit.capac))) {
			return RAM_get_mbyte(addr);
		}
    } /* otherwise, try the multibus */
    return multibus_get_mbyte(addr);
}

/*  get a word from memory */

uint16 get_mword(uint16 addr)
{
    uint16 val;

    val = get_mbyte(addr);
    val |= (get_mbyte(addr+1) << 8);
    return val;
}

/*  put a byte to memory - handle RAM, ROM, I/O, and Multibus memory */

void put_mbyte(uint16 addr, uint8 val)
{
    /* if local EPROM handle it */
    if ((i8255_unit[0].u5 & 0x01) && (addr >= EPROM_unit.u3) && ((uint16)addr <= (EPROM_unit.u3 + EPROM_unit.capac))) {
        sim_printf("Write to R/O memory address %04X - ignored\n", addr);
        return;
    } /* if local RAM handle it */
    if ((i8255_unit[0].u5 & 0x02) && (addr >= RAM_unit.u3) && ((uint16)addr <= (RAM_unit.u3 + RAM_unit.capac))) {
        RAM_put_mbyte(addr, val);
        return;
    } /* otherwise, try the multibus */
    multibus_put_mbyte(addr, val);
}

/*  put a word to memory */

void put_mword(uint16 addr, uint16 val)
{
    put_mbyte(addr, val & 0xff);
    put_mbyte(addr+1, val >> 8);
}

/* end of iSBC80-10.c */
