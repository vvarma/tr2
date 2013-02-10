package com.tr2.instrument;

import com.tr2.exceptions.UnknownLevelException;

public enum Level {

	BUY, HOLD, SELL;
	Level addLevel(Level level) {
		switch (this) {
		case BUY:
			switch (level) {
			case BUY:
				return BUY;
			case HOLD:
				return HOLD;
			case SELL:
				return SELL;
			}
		case HOLD:
			switch (level) {
			case BUY:
				return BUY;
			case HOLD:
				return HOLD;
			case SELL:
				return SELL;
			}
		case SELL:
			switch (level) {
			case BUY:
				return HOLD;
			case HOLD:
				return HOLD;
			case SELL:
				return SELL;
			}
		}

		throw new UnknownLevelException();
		

	}
}
