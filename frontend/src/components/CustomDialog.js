import React from "react";
import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";

export function CustomDialog({open, onClose, successAction, successTitle, content, title}) {
    return (
        <Dialog
            open={open}
            onClose={onClose}
            maxWidth="md"
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
                {successAction && <Button onClick={successAction}>{successTitle}</Button>}
                <Button onClick={onClose}>Закрыть</Button>
            </DialogActions>
        </Dialog>
    )
}