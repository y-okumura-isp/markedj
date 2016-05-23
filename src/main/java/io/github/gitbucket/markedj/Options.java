package io.github.gitbucket.markedj;

public class Options {

    private boolean gfm = true;
    private boolean tables = true;
    private boolean breaks = false;
    private boolean pedantic = false;
    private boolean sanitize = false;
    // TOOO private Object sanitizer = null;
//    private boolean mangle = false;
//    private boolean smartLists = false;
//    private boolean silent = false;
    // TODO private Object highlight = null;
    private String langPrefix = "lang-";
//    private boolean smartypants = false;
    private String headerPrefix = "";
    private boolean headerIdSequential;
    private boolean xhtml = false;
    private boolean linkTargetBlank;

    private int sequentialId = 0;
    public void initSequentialId() {
        sequentialId = 0;
    }
    public int SequentialId() {
        sequentialId++;
        return sequentialId;
    }
    
    
    public void setGfm(boolean gfm) {
        this.gfm = gfm;
    }

    public void setTables(boolean tables) {
        this.tables = tables;
    }

    public void setBreaks(boolean breaks) {
        this.breaks = breaks;
    }

    public void setPedantic(boolean pedantic) {
        this.pedantic = pedantic;
    }

    public void setSanitize(boolean sanitize) {
        this.sanitize = sanitize;
    }

//    public void setMangle(boolean mangle) {
//        this.mangle = mangle;
//    }
//
//    public void setSmartLists(boolean smartLists) {
//        this.smartLists = smartLists;
//    }

//    public void setSilent(boolean silent) {
//        this.silent = silent;
//    }

    public void setLangPrefix(String langPrefix) {
        this.langPrefix = langPrefix;
    }

//    public void setSmartypants(boolean smartypants) {
//        this.smartypants = smartypants;
//    }

    public void setHeaderPrefix(String headerPrefix) {
        this.headerPrefix = headerPrefix;
    }

    public void setXhtml(boolean xhtml) {
        this.xhtml = xhtml;
    }

    public boolean isGfm() {
        return gfm;
    }

    public boolean isTables() {
        return tables;
    }

    public boolean isBreaks() {
        return breaks;
    }

    public boolean isPedantic() {
        return pedantic;
    }

    public boolean isSanitize() {
        return sanitize;
    }

//    public boolean isMangle() {
//        return mangle;
//    }
//
//    public boolean isSmartLists() {
//        return smartLists;
//    }

//    public boolean isSilent() {
//        return silent;
//    }

    public String getLangPrefix() {
        return langPrefix;
    }

//    public boolean isSmartypants() {
//        return smartypants;
//    }

    public String getHeaderPrefix() {
        return headerPrefix;
    }

    public boolean isXhtml() {
        return xhtml;
    }

	/**
	 * @return the linkTargetBlank
	 */
	public boolean isLinkTargetBlank() {
		return linkTargetBlank;
	}

	/**
	 * @param linkTargetBlank the linkTargetBlank to set
	 */
	public void setLinkTargetBlank(boolean linkTargetBlank) {
		this.linkTargetBlank = linkTargetBlank;
	}

    /**
     * Get headerIdSequential
     * @return the headerIdSequential
     */
    public boolean isHeaderIdSequential() {
        return headerIdSequential;
    }

    /**
     * Set headerIdSequential
     * @param headerIdSequential the headerIdSequential to set
     */
    public void setHeaderIdSequential(boolean headerIdSequential) {
        this.headerIdSequential = headerIdSequential;
    }
}
