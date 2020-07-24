package com.mckesson.inferno.loginsvc.dao;

import com.mckesson.inferno.loginsvc.exception.SequenceException;

public interface SequenceDao {

	long getNextSequenceId(String key) throws SequenceException;
}
