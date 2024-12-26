package com.kk.app.backend.entity;

public enum Status {

		CREATED,
	    ACTIVE,          // Dashboard is active and accessible.
	    INACTIVE,        // Dashboard is inactive and not currently in use.
	    DRAFT,           // Dashboard is being created or edited and not finalized.
	    ARCHIVED,        // Dashboard is archived and not displayed to users.
	    PUBLISHED,       // Dashboard is finalized and available to users.
	    DELETED,         // Dashboard is marked for deletion but may not be permanently removed.
	    UNDER_REVIEW     // Dashboard is undergoing review or approval process.
	

}
