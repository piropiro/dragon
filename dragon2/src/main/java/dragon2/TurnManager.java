package dragon2;

import java.awt.Point;
import java.util.Iterator;
import java.util.Vector;

import dragon2.common.Body;
import dragon2.common.constant.Colors;
import dragon2.common.constant.Kinds;
import dragon2.common.constant.MoveType;
import dragon2.common.constant.Types;
import dragon2.cpu.EnemyTurn;
import dragon2.paint.PaintBase;

public class TurnManager extends ActionBase {

	public TurnManager(Vector vector, UnitWorks unitworks) {
		Charas = vector;
		uw = unitworks;
	}

	public void reset() {
		turn = 0;
	}

	public int getTurn() {
		return turn;
	}

	public void change(int i) {
		type = i;
		if (i == 0)
			turn++;
		action();
	}

	public void actionMain() {
		switch (type) {
		case 0: // '\0'
			mensTurnStart();
			break;

		case 1: // '\001'
			enemyTurnStart();
			break;


		}
	}

	private void mensTurnStart() {
		turnChange(true);
		PaintBase.map.setBasicPaint();
		PaintBase.map.repaint();
	}

	private void enemyTurnStart() {
		turnChange(false);
		uw.limitOver();
		EnemyTurn enemyturn = new EnemyTurn();
		PaintBase.map.setPaintListener(enemyturn);
	}

	private void turnChange(boolean flag) {
		PaintBase.V.clear(1, 0, 0);
		PaintBase.V.clear(3, 0, 0);
		PaintBase.V.clear(4, 0, 0);
		for (Iterator iterator = Charas.iterator(); iterator.hasNext();) {
			Body body = (Body) iterator.next();
			if (body.isAlive()) {
				if (!body.isType(Types.S_WAIT))
					PaintBase.V.S(5, 0, body.x, body.y, 0);
				body.setTypeState(Types.SORA, false);
				body.setTypeState(Types.RIKU, false);
				setTikei(body, flag);
				setPoison(body, flag);
				setHeal(body, flag);
				setBersek(body, flag);
				setStatus(body, Types.ATTACK_UP, 4);
				setStatus(body, Types.GUARD_UP, 5);
				setStatus(body, Types.CLOSE, 3);
				setStatus(body, Types.OIL, 10);
				setStatus(body, Types.CHARM, 6);
				setStatus(body, Types.ANTI_SLEEP, 1);
				if (body.isType(Types.S_WAIT)) {
					body.setTypeState(Types.S_WAIT, false);
					if (body.isType(Types.CHARM) || body.isType(Types.ANTI_SLEEP))
						body.setTypeState(Types.S_LOCK, true);
				}
			}
		}

	}

	private void setStatus(Body body, Types i, int j) {
		if (!body.isType(Types.S_WAIT))
			body.setTypeState(i, false);
		else if (body.isType(i))
			PaintBase.V.S(5, 0, body.x, body.y, j);
	}

	private void setTikei(Body body, boolean flag) {
		if (Walk.getTikei(body) == 1)
			return;
		Point point = new Point(body.x, body.y);
		switch (PaintBase.V.G(0, 0, body.x, body.y)) {
		case 17: // '\021'
			if (Colors.isPlayer(body) != flag)
				return;
			if (body.hp == body.hpMax && !body.isType(Types.POISON)) {
				return;
			} else {
				body.setTypeState(Types.POISON, false);
				uw.setAnime(1, -1, point, point);
				body.hp += Math.max(2, body.hpMax / 4);
				body.hp = Math.min(body.hp, body.hpMax);
				return;
			}

		case 3: // '\003'
		case 4: // '\004'
			if (body.isType(Types.ANTI_ALL))
				return;
			if (body.isType(Types.SWIM_ABLE))
				return;
			if (body.getMoveType() == MoveType.SWIM)
				return;
			if (body.getMoveType() == MoveType.TWIN)
				return;
			body.setTypeState(Types.CLOSE, true);
			if (body.isType(Types.ANTI_SLEEP))
				return;
			if (body.isType(Types.CHARM)) {
				return;
			} else {
				body.setTypeState(Types.S_WAIT, true);
				return;
			}

		case 7: // '\007'
			if (body.isType(Types.ANTI_ALL))
				return;
			if (body.isType(Types.ANTI_POISON)) {
				return;
			} else {
				body.setTypeState(Types.POISON, true);
				return;
			}

		case 8: // '\b'
			if (body.isType(Types.ANTI_ALL))
				return;
			body.setTypeState(Types.OIL, true);
			if (body.isType(Types.ANTI_SLEEP))
				return;
			if (body.isType(Types.CHARM)) {
				return;
			} else {
				body.setTypeState(Types.S_WAIT, true);
				return;
			}

		case 9: // '\t'
			if (Colors.isPlayer(body) != flag)
				return;
			if (body.isType(Types.FIRE_0))
				return;
			int i = 4;
			if (body.isType(Types.FIRE_200))
				i *= 2;
			if (body.isType(Types.OIL))
				i *= 2;
			if (body.isType(Types.FIRE_50))
				i /= 2;
			uw.setAnime(1, -10, point, point);
			body.hp -= Math.max(2, (body.hpMax * i) / 16);
			body.hp = Math.max(1, body.hp);
			// fall through

		case 5: // '\005'
		case 6: // '\006'
		case 10: // '\n'
		case 11: // '\013'
		case 12: // '\f'
		case 13: // '\r'
		case 14: // '\016'
		case 15: // '\017'
		case 16: // '\020'
		default:
			return;
		}
	}

	private void setPoison(Body body, boolean flag) {
		if (!body.isType(Types.POISON))
			return;
		if (Colors.isPlayer(body) != flag || body.hp == 1) {
			PaintBase.V.S(5, 0, body.x, body.y, 2);
		} else {
			Point point = new Point(body.x, body.y);
			uw.setAnime(-7, 2, point, point);
			body.hp -= Math.max(1, body.hpMax / 8);
			body.hp = Math.max(1, body.hp);
		}
		if (body.hp == 1)
			body.setTypeState(Types.POISON, false);
	}

	private void setBersek(Body body, boolean flag) {
		if (body.kind != Kinds.DOLL)
			return;
		if (!body.isType(Types.BERSERK))
			return;
		if (Colors.isPlayer(body) != flag) {
			PaintBase.V.S(5, 0, body.x, body.y, 12);
		} else {
			body.str = Math.max(0, body.str - 1);
			body.def = Math.max(0, body.def - 1);
			body.mst = Math.max(0, body.mst - 1);
			body.mdf = Math.max(0, body.mdf - 1);
			body.hit = Math.max(0, body.hit - 1);
			body.mis = Math.max(0, body.mis - 1);
			Point point = new Point(body.x, body.y);
			uw.setAnime(-7, 12, point, point);
		}
	}

	private void setHeal(Body body, boolean flag) {
		if (!body.isType(Types.HEAL))
			return;
		if (Colors.isPlayer(body) != flag || body.hp == body.hpMax)
			PaintBase.V.S(5, 0, body.x, body.y, 7);
		else if (body.mst > 0) {
			Point point = new Point(body.x, body.y);
			uw.setAnime(-7, 7, point, point);
			body.mst = Math.max(0, body.mst - 2);
			body.hp = Math.min(body.hpMax, body.hp + body.hpMax / 2);
		} else {
			body.setTypeState(Types.HEAL, false);
		}
	}

	public void setSelectBody(Body body) {
	}

	public void mouseMoved(Point point) {
	}

	public void leftPressed() {
	}

	public void rightPressed() {
		uw.waitFast();
	}

	public void rightReleased() {
		uw.waitSlow();
	}

	static final int MENS = 0;
	static final int ENEMY = 1;
	static final int REMOTE = 2;
	private int turn;
	private int type;
	private Vector Charas;
	private UnitWorks uw;
}
