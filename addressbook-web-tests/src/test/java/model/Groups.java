package model;

import com.google.common.collect.ForwardingSet;
import org.testng.Assert;

import java.util.*;

public class Groups extends ForwardingSet<GroupData>{

    private Set<GroupData> delegate;

    public Groups(Groups groups) {
        this.delegate = new HashSet<GroupData>(groups.delegate);
    }

    public Groups() {
        this.delegate = new HashSet<GroupData>();
    }

    public Groups(Collection<GroupData> groups) {
        this.delegate = new HashSet<GroupData>(groups);
    }

    @Override
    protected Set<GroupData> delegate() {
        return delegate;
    }

    public Groups withAdded (GroupData group){
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups withOut (GroupData group){
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }

    public String FindNotAddedGroup(Groups contactGroups){
        Groups groups = new Groups(this);
        System.out.println("group from base is: " + groups);
        System.out.println("contact group is: " + contactGroups);

        Iterator<GroupData> iteratorBase = groups.iterator();
        Iterator<GroupData> iteratorContact = contactGroups.iterator();
        while (iteratorBase.hasNext()) {
            boolean groupIsNotFound = true;
            String groupBaseName = iteratorBase.next().getName();
            while (iteratorContact.hasNext()) {
                String groupContactName = iteratorContact.next().getName();
                if(groupBaseName.equals(groupContactName)) {
                    groupIsNotFound = false;
                    break;
                }
            }
            if (groupIsNotFound)
                return groupBaseName;
        }

        return "";
    }

}
