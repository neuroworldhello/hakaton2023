import React from "react";
import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";

export function CustomDialog({open, onClose, successButtonAction, successButtonTitle, content, title}) {
    return (
        <Dialog
            open={open}
            onClose={onClose}
            maxWidth="sm"
            fullWidth={true}
        >
            <DialogTitle>
                {title}
            </DialogTitle>
            <DialogContent>
                <DialogContentText>
                    {content}
                </DialogContentText>
            </DialogContent>
            <DialogActions>
                {successButtonAction && <Button onClick={successButtonAction}>{successButtonTitle}</Button>}
            </DialogActions>
        </Dialog>
    )
}