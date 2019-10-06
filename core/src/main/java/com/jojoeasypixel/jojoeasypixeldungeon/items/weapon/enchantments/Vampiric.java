/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.jojoeasypixel.jojoeasypixeldungeon.items.weapon.enchantments;

import com.jojoeasypixel.jojoeasypixeldungeon.actors.Char;
import com.jojoeasypixel.jojoeasypixeldungeon.effects.Speck;
import com.jojoeasypixel.jojoeasypixeldungeon.items.weapon.Weapon;
import com.jojoeasypixel.jojoeasypixeldungeon.sprites.CharSprite;
import com.jojoeasypixel.jojoeasypixeldungeon.sprites.ItemSprite;
import com.jojoeasypixel.jojoeasypixeldungeon.sprites.ItemSprite.Glowing;

public class Vampiric extends Weapon.Enchantment {

	private static ItemSprite.Glowing RED = new ItemSprite.Glowing( 0x660022 );
	
	@Override
	public int proc( Weapon weapon, Char attacker, Char defender, int damage ) {
		
		//heals for 2.5-15% of damage dealt, based on missing HP
		float missingPercent = (attacker.HT - attacker.HP) / (float)attacker.HT;
		float healPercent = .025f + (missingPercent * 0.125f);
		int healAmt = Math.round(healPercent * damage);
		healAmt = Math.min( healAmt, attacker.HT - attacker.HP );
		
		if (healAmt > 0 && attacker.isAlive()) {
		
			attacker.HP += healAmt;
			attacker.sprite.emitter().start( Speck.factory( Speck.HEALING ), 0.4f, 1 );
			attacker.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( healAmt ) );
			
		}

		return damage;
	}
	
	@Override
	public Glowing glowing() {
		return RED;
	}
}
